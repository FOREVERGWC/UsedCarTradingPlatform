package org.example.springboot.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.springboot.biz.domain.dto.CarAuditeDto;
import org.example.springboot.biz.domain.entity.CarAudite;
import org.example.springboot.biz.domain.vo.CarAuditeVo;
import org.example.springboot.biz.service.ICarAuditeService;
import org.example.springboot.common.domain.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 二手车审核前端控制器
 * </p>
 */
@RestController
@RequestMapping("/car/audite")
@Tag(name = "二手车审核", description = "二手车审核")
public class CarAuditeController {
    @Resource
    private ICarAuditeService carAuditeService;

    /**
     * 添加、修改二手车审核
     *
     * @param carAudite 二手车审核
     * @return 结果
     */
    @PostMapping
    @Operation(summary = "添加、修改二手车审核", description = "添加、修改二手车审核", method = "POST")
    public Result<Void> save(@RequestBody CarAudite carAudite) {
        carAuditeService.saveOrUpdate(carAudite);
        return Result.success();
    }

    /**
     * 删除二手车审核
     *
     * @param ids ID列表
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除二手车审核", description = "删除二手车审核", method = "DELETE")
    public Result<Void> removeBatchByIds(@PathVariable List<Long> ids) {
        carAuditeService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询二手车审核列表
     *
     * @param dto 二手车审核
     * @return 结果
     */
    @GetMapping("/list")
    @Operation(summary = "查询二手车审核列表", description = "查询二手车审核列表", method = "GET")
    public Result<List<CarAuditeVo>> getList(CarAuditeDto dto) {
        List<CarAuditeVo> list = carAuditeService.getList(dto);
        return Result.success(list);
    }

    /**
     * 查询二手车审核分页
     *
     * @param dto 二手车审核
     * @return 结果
     */
    @GetMapping("/page")
    @Operation(summary = "查询二手车审核分页", description = "查询二手车审核分页", method = "GET")
    public Result<IPage<CarAuditeVo>> getPage(CarAuditeDto dto) {
        IPage<CarAuditeVo> page = carAuditeService.getPage(dto);
        return Result.success(page);
    }

    /**
     * 查询二手车审核
     *
     * @param dto 二手车审核
     * @return 结果
     */
    @GetMapping
    @Operation(summary = "查询二手车审核", description = "查询二手车审核", method = "GET")
    public Result<CarAuditeVo> getOne(CarAuditeDto dto) {
        CarAuditeVo vo = carAuditeService.getOne(dto);
        return Result.success(vo);
    }
}
