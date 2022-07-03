package api.initializer;

import api.entity.BoundingBox;
import api.exception.InvalidArgumentException;
import api.service.BoundingBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.*;

@Component
public class Initializer {

    private final BoundingBoxService service;

    @Autowired
    public Initializer(BoundingBoxService service) {
        this.service = service;
    }

    @PostConstruct
    private synchronized void init() throws InvalidArgumentException {
        service.create(new BoundingBox("TestObject", new Point(5,10), new Point(115,280)));
    }
}