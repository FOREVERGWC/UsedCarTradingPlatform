package org.example.springboot.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.springboot.system.common.enums.DeleteEnum;
import org.example.springboot.biz.domain.dto.ArticleCategoryDto;
import org.example.springboot.biz.domain.entity.ArticleCategory;
import org.example.springboot.system.domain.entity.User;
import org.example.springboot.biz.domain.vo.ArticleCategoryVo;
import org.example.springboot.biz.mapper.ArticleCategoryMapper;
import org.example.springboot.biz.service.IArticleCategoryService;
import org.example.springboot.system.service.IUserService;
import org.example.springboot.common.utils.DataUtils;
import org.example.springboot.system.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章类别服务实现类
 * </p>
 */
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements IArticleCategoryService {
    @Resource
    private IUserService userService;

    @Override
    public boolean save(ArticleCategory entity) {
        Long userId = UserUtils.getLoginUserId();
        entity.setUserId(userId);
        entity.setDeleted(DeleteEnum.NORMAL.getCode());
        return super.save(entity);
    }

    @Override
    public boolean saveOrUpdate(ArticleCategory entity) {
        if (entity.getId() == null) {
            return save(entity);
        }
        return super.updateById(entity);
    }

    @Override
    public List<ArticleCategoryVo> getList(ArticleCategoryDto dto) {
        List<ArticleCategory> articleCategoryList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(articleCategoryList)) {
            return List.of();
        }
        // 作者
        List<Long> userIdList = articleCategoryList.stream().map(ArticleCategory::getUserId).toList();
        List<User> userList = userService.listByIds(userIdList);
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 组装VO
        return articleCategoryList.stream().map(item -> {
            ArticleCategoryVo vo = new ArticleCategoryVo();
            BeanUtils.copyProperties(item, vo);
            vo.setUser(userMap.getOrDefault(item.getUserId(), User.builder().name("已删除").build()));
            return vo;
        }).toList();
    }

    @Override
    public List<ArticleCategoryVo> getTree(ArticleCategoryDto dto) {
        List<ArticleCategoryVo> vos = getList(dto);
        // 树
        return DataUtils.listToTree(vos, ArticleCategoryVo::getParentId, ArticleCategoryVo::setChildren, ArticleCategoryVo::getId, 0L, null, null);
    }

    @Override
    public IPage<ArticleCategoryVo> getPage(ArticleCategoryDto dto) {
        Page<ArticleCategory> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 作者
        List<Long> userIdList = info.getRecords().stream().map(ArticleCategory::getUserId).toList();
        List<User> userList = userService.listByIds(userIdList);
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 组装VO
        return info.convert(item -> {
            ArticleCategoryVo vo = new ArticleCategoryVo();
            BeanUtils.copyProperties(item, vo);
            vo.setUser(userMap.getOrDefault(item.getUserId(), User.builder().name("已删除").build()));
            return vo;
        });
    }

    @Override
    public ArticleCategoryVo getOne(ArticleCategoryDto dto) {
        ArticleCategory one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 作者
        User user = Optional.ofNullable(userService.getById(one.getUserId())).orElse(User.builder().name("已删除").build());
        // 组装VO
        ArticleCategoryVo vo = new ArticleCategoryVo();
        BeanUtils.copyProperties(one, vo);
        vo.setUser(user);
        return vo;
    }

    /**
     * 组装查询包装器
     *
     * @param entity 文章类别
     * @return 结果
     */
    private LambdaQueryChainWrapper<ArticleCategory> getWrapper(ArticleCategoryDto entity) {
        // TODO 这是后台接口，判断用户权限，若非管理员则只能查看自己的文章，添加一个前台接口只允许查看已发布、公开状态的文章
        LambdaQueryChainWrapper<ArticleCategory> wrapper = lambdaQuery()
                .eq(entity.getId() != null, ArticleCategory::getId, entity.getId())
                .like(StrUtil.isNotBlank(entity.getName()), ArticleCategory::getName, entity.getName())
                .eq(entity.getParentId() != null, ArticleCategory::getParentId, entity.getParentId())
                .eq(entity.getUserId() != null, ArticleCategory::getUserId, entity.getUserId())
                .eq(entity.getDeleted() != null, ArticleCategory::getDeleted, entity.getDeleted());
        if (entity instanceof ArticleCategoryDto dto) {
            Map<String, Object> params = dto.getParams();
            // 创建时间
            Object startCreateTime = params == null ? null : params.get("startCreateTime");
            Object endCreateTime = params == null ? null : params.get("endCreateTime");

            wrapper.between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime),
                    ArticleCategory::getCreateTime,
                    startCreateTime, endCreateTime);
        }
        return wrapper;
    }
}
