package trainingportal.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Material {

    @NotNull
    private Long materialId;
    @Size(min = 1, max = 40)
    @NotNull
    private Long lessonId;
    @Size(min = 1, max = 30)
    private String materialDescription;

    @Size(min = 10, max = 220)
    @NotNull


    public Material() {
    }

    public Material(@NotNull Long materialId, @Size(min = 1, max = 40) @NotNull Long lessonId, @Size(min = 1, max = 30) String materialDescription) {
        this.materialId = materialId;
        this.lessonId = lessonId;
        this.materialDescription = materialDescription;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }
}
