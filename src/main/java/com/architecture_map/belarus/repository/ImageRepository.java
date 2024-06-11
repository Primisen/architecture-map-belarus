package com.architecture_map.belarus.repository;

import com.architecture_map.belarus.entity.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository  extends JpaRepository<Image, Integer> {
}
