package api.config;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Configuration
public class YamlConfig {

    @Value("${workspace.image.width}")
    private double width;
    @Value("${workspace.image.height}")
    private double height;

    @Value("${workspace.boundingBox.minWidth}")
    private double minWidth;
    @Value("${workspace.boundingBox.minHeight}")
    private double minHeight;

}
