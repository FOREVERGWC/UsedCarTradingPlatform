package org.example.springboot.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.springboot.biz.domain.dto.CarDto;
import org.example.springboot.biz.domain.entity.Car;
import org.example.springboot.biz.domain.vo.CarVo;
import org.example.springboot.biz.service.ICarService;
import org.example.springboot.common.domain.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 二手车前端控制器
 * </p>
 */
@RestController
@RequestMapping("/car")
@Tag(name = "二手车", description = "二手车")
public class CarController {
    @Resource
    private ICarService carService;

    /**
     * 添加、修改二手车
     *
     * @param car 二手车
     * @return 结果
     */
    @PostMapping
    @Operation(summary = "添加、修改二手车", description = "添加、修改二手车", method = "POST")
    public Result<Void> save(@RequestBody Car car) {
        carService.saveOrUpdate(car);
        return Result.success();
    }

    /**
     * 删除二手车
     *
     * @param ids ID列表
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除二手车", description = "删除二手车", method = "DELETE")
    public Result<Void> removeBatchByIds(@PathVariable List<Long> ids) {
        carService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询二手车列表
     *
     * @param dto 二手车
     * @return 结果
     */
    @GetMapping("/list")
    @Operation(summary = "查询二手车列表", description = "查询二手车列表", method = "GET")
    public Result<List<CarVo>> getList(CarDto dto) {
        List<CarVo> list = carService.getList(dto);
        return Result.success(list);
    }

    /**
     * 查询二手车分页
     *
     * @param dto 二手车
     * @return 结果
     */
    @GetMapping("/page")
    @Operation(summary = "查询二手车分页", description = "查询二手车分页", method = "GET")
    public Result<IPage<CarVo>> getPage(CarDto dto) {
        IPage<CarVo> page = carService.getPage(dto);
        return Result.success(page);
    }

    /**
     * 查询二手车
     *
     * @param dto 二手车
     * @return 结果
     */
    @GetMapping
    @Operation(summary = "查询二手车", description = "查询二手车", method = "GET")
    public Result<CarVo> getOne(CarDto dto) {
        CarVo vo = carService.getOne(dto);
        return Result.success(vo);
    }
}
