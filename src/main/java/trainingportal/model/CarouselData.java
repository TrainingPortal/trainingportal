package trainingportal.model;

import javax.persistence.*;

@Entity
@Table(name = "carousel_data")
public class CarouselData {

    @Id
    @Column
    private int carouselId;
    @Lob
    @Column
    private byte[] carouselImage;
    @Column
    private String carouselButton;

    protected CarouselData() {
    }

    public CarouselData(int carouselId, byte[] carouselImage, String carouselButton) {
        this.carouselId = carouselId;
        this.carouselImage = carouselImage;
        this.carouselButton = carouselButton;
    }

    public int getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(int carousel_id) {
        this.carouselId = carousel_id;
    }

    public byte[] getCarouselImage() {
        return carouselImage;
    }

    public void setCarouselImage(byte[] carousel_image) {
        this.carouselImage = carousel_image;
    }

    public String getCarouselButton() {
        return carouselButton;
    }

    public void setCarouselButton(String carousel_button) {
        this.carouselButton = carousel_button;
    }

}
