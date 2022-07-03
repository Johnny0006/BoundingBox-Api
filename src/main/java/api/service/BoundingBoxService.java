package api.service;

import api.config.YamlConfig;
import api.entity.BoundingBox;
import api.exception.InvalidArgumentException;
import api.repository.BoundingBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BoundingBoxService {

    private final BoundingBoxRepository repository;
    private final YamlConfig config;

    @Autowired
    public BoundingBoxService(BoundingBoxRepository repository, YamlConfig config) {
        this.repository = repository;
        this.config = config;
    }

    public Optional<BoundingBox> find(long id) {
        return this.repository.findById(id);
    }

    public List<BoundingBox> findAll() {

        return this.repository.findAll();
    }

    @Transactional
    public BoundingBox create(BoundingBox boundingBox) throws InvalidArgumentException {
        isValid(boundingBox);
        return this.repository.save(boundingBox);
    }


    @Transactional
    public void delete(BoundingBox boundingBox) {
        this.repository.delete(boundingBox);
    }

    @Transactional
    public void delete(long id) {
        this.repository.deleteById(id);
    }

    @Transactional
    public void update(BoundingBox old, BoundingBox updated) throws InvalidArgumentException {
        isValid(updated);
        old.setName(updated.getName());
        old.setLeftUp(updated.getLeftUp());
        old.setRightDown(updated.getRightDown());
        this.repository.save(old);
    }


    private void isValid(BoundingBox boundingBox) throws InvalidArgumentException {

        double x1 = boundingBox.getLeftUp().getX();
        double x2 = boundingBox.getRightDown().getX();

        double y1 = boundingBox.getLeftUp().getY();
        double y2 = boundingBox.getRightDown().getY();

        double width = config.getWidth();
        double height = config.getHeight();

        double minWidth = config.getMinWidth();
        double minHeight = config.getMinHeight();

        if (x1 < 0 || x1 > width || y1 < 0 || y1 > height)
            throw new InvalidArgumentException("LeftUp point out of boundaries");
        else if (x2 < 0 || x2 > width || y2 < 0 || y2 > height)
            throw new InvalidArgumentException("RightDown point out of boundaries");
        else if (Math.abs(x2 - x1) < minWidth || Math.abs(y2 - y1) < minHeight)
            throw new InvalidArgumentException("BoundingBox is too small");
    }
}
