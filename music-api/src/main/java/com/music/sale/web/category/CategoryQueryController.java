package com.music.sale.web.category;

import com.music.sale.application.category.port.inport.CategoryQueryUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.domain.category.enums.CategoryType;
import com.music.sale.web.category.mapper.CategoryQueryWebMapper;
import com.music.sale.web.category.response.CategoryQueryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Category", description = "카테고리 API")
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryQueryController {

  private final CategoryQueryUseCase categoryQueryUseCase;
  private final CategoryQueryWebMapper categoryQueryWebMapper;

  public CategoryQueryController(
      CategoryQueryUseCase categoryQueryUseCase, CategoryQueryWebMapper categoryQueryWebMapper) {
    this.categoryQueryUseCase = categoryQueryUseCase;
    this.categoryQueryWebMapper = categoryQueryWebMapper;
  }

  @Operation(summary = "카테고리 상세 조회", description = "카테고리 ID로 카테고리 정보를 조회합니다.")
  @GetMapping("/{id}")
  public ApiResponse<CategoryQueryResponse> getCategoryById(@PathVariable Long id) {
    var category = categoryQueryUseCase.getCategoryById(id);
    return ApiResponse.success(categoryQueryWebMapper.toResponse(category));
  }

  @Operation(summary = "전체 카테고리 조회", description = "모든 카테고리를 조회합니다.")
  @GetMapping("/all")
  public ApiResponse<List<CategoryQueryResponse>> getAllCategories() {
    var categories = categoryQueryUseCase.getAllCategories();
    return ApiResponse.success(
        categories.stream().map(categoryQueryWebMapper::toResponse).collect(Collectors.toList()));
  }

  @Operation(summary = "타입별 카테고리 조회", description = "특정 타입의 카테고리들을 조회합니다.")
  @GetMapping("/type")
  public ApiResponse<List<CategoryQueryResponse>> getCategoriesByType(
      @RequestParam CategoryType type) {
    var categories = categoryQueryUseCase.getCategoriesByType(type);
    return ApiResponse.success(
        categories.stream().map(categoryQueryWebMapper::toResponse).collect(Collectors.toList()));
  }

  @Operation(summary = "최상위 카테고리 조회", description = "부모가 없는 최상위 카테고리들을 조회합니다.")
  @GetMapping("/root")
  public ApiResponse<List<CategoryQueryResponse>> getRootCategories() {
    var categories = categoryQueryUseCase.getRootCategories();
    return ApiResponse.success(
        categories.stream().map(categoryQueryWebMapper::toResponse).collect(Collectors.toList()));
  }

  @Operation(summary = "하위 카테고리 조회", description = "특정 부모 카테고리의 하위 카테고리들을 조회합니다.")
  @GetMapping("/parent/{parentId}")
  public ApiResponse<List<CategoryQueryResponse>> getCategoriesByParentId(
      @PathVariable Long parentId) {
    var categories = categoryQueryUseCase.getCategoriesByParentId(parentId);
    return ApiResponse.success(
        categories.stream().map(categoryQueryWebMapper::toResponse).collect(Collectors.toList()));
  }
}
