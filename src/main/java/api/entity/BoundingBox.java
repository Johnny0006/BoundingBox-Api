package api.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.awt.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "BoundingBoxes")
public class BoundingBox {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    private Point leftUp;

    private Point rightDown;

    public BoundingBox(String name, Point leftUp, Point rightDown){
        this.name = name;
        this.leftUp = leftUp;
        this.rightDown = rightDown;
    }


}
