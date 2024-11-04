package org.example.springboot.generator.db.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据库表前端控制器
 * </p>
 */
@RestController
@RequestMapping("/db/table")
@Tag(name = "数据库表接口", description = "数据库表接口")
public class DbTableController {
}
