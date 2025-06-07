package com.music.sale.domain.category

/**
 * 카테고리 타입을 정의하는 enum
 */
enum class CategoryType {
    PRODUCT,    // 제품 카테고리
    STORE,      // 상점 카테고리
    BOARD,      // 게시판 카테고리
    SEARCH,     // 검색 카테고리
    STATISTICS  // 통계 카테고리
}

/**
 * 카테고리 도메인 모델
 * 계층 구조를 가진 카테고리를 표현
 */
class Category(
    val id: Long,
    val name: String,
    val type: CategoryType,
    var parent: Category?,
    var path: String,
    var depth: Int,
    var isActive: Boolean = true
) {
    private val children = mutableListOf<Category>()

    fun isRoot(): Boolean = parent == null

    fun isLeaf(): Boolean = children.isEmpty()

    fun addChild(child: Category) {
        require(child.depth == this.depth + 1) { "자식 카테고리의 깊이는 부모보다 1 커야 합니다" }
        require(child.type == this.type) { "자식 카테고리는 부모와 동일한 타입이어야 합니다" }
        children.add(child)
    }

    fun removeChild(child: Category) {
        children.remove(child)
    }

    fun moveTo(newParent: Category?) {
        require(newParent != this) { "자기 자신을 부모로 지정할 수 없습니다" }
        require(newParent?.isDescendantOf(this) != true) { "자식 카테고리를 부모로 지정할 수 없습니다" }
        require(newParent?.type == this.type) { "이동할 카테고리는 동일한 타입이어야 합니다" }
        
        parent?.removeChild(this)
        newParent?.addChild(this)
        parent = newParent
        updatePath()
    }

    private fun updatePath() {
        path = if (parent == null) {
            "/$id"
        } else {
            "${parent?.path}/$id"
        }
        depth = path.count { it == '/' } - 1
    }

    fun isDescendantOf(category: Category): Boolean {
        var current = parent
        while (current != null) {
            if (current == category) return true
            current = current.parent
        }
        return false
    }

    fun deactivate() {
        isActive = false
    }

    fun activate() {
        isActive = true
    }

    companion object {
        fun createRoot(name: String, type: CategoryType): Category {
            return Category(
                id = 0L, // ID는 영속성 계층에서 할당
                name = name,
                type = type,
                parent = null,
                path = "",
                depth = 0
            )
        }
    }
} 