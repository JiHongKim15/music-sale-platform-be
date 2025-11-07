package com.music.sale.persistence.image.entity;

import com.music.sale.persistence.common.BaseEntity;
import jakarta.persistence.*;

@Entity(name = "ImageProductImageEntity")
@Table(name = "product_image")
public class ProductImageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(
//        name = "product_item_id",
//        nullable = false
//    )
//    private Product product;

    @Column(name = "url", nullable = false, length = 500)
    private String url;

    @Column(name = "is_thumbnail", nullable = false)
    private boolean isThumbnail = false;

    @Column(name = "image_order", nullable = false)
    private Integer imageOrder = 1;

    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    @Column(name = "file_name", nullable = false, length = 255)
    private String fileName;

    @Column(name = "file_type", nullable = false, length = 100)
    private String fileType;

    public ProductImageEntity() {}

    public ProductImageEntity(Long id,
//        Product product,
                       String url,
                       Boolean isThumbnail,
                       Integer imageOrder,
                       Long fileSize,
                       String fileName,
                       String fileType) {
        this.id = id;
//        this.product = product;
        this.url = url;
        this.isThumbnail = isThumbnail;
        this.imageOrder = imageOrder;
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

//    public Product getProduct() { return product; }
//    public void setProduct(Product product) { this.product = product; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Boolean getIsThumbnail() { return isThumbnail; }
    public void setIsThumbnail(Boolean isThumbnail) { this.isThumbnail = isThumbnail; }

    public Integer getImageOrder() { return imageOrder; }
    public void setImageOrder(Integer imageOrder) { this.imageOrder = imageOrder; }

    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
}


