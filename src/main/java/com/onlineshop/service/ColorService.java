package com.onlineshop.service;

import com.onlineshop.domain.Color;
import com.onlineshop.dto.ColorDTO;
import com.onlineshop.repository.ColorRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Transactional(readOnly = true)
@Service
public class ColorService {
    private final ColorRepo colorRepo;

    public ColorService(ColorRepo colorRepo) {
        this.colorRepo = colorRepo;
    }

    public ColorDTO getEmptyDTO(){
        ColorDTO colorDTO = new ColorDTO();
        colorDTO.setId(0l);
        colorDTO.setName("");
        return colorDTO;
    }

    public ColorDTO convertEntityToDTO(Color color){
        ColorDTO colorDTO = new ColorDTO();
        colorDTO.setId(color.getId());
        colorDTO.setName(color.getName().trim());
        return colorDTO;
    }

    public Iterable<ColorDTO> convertAllEntityToDTO(Iterable<Color> colors){
        return StreamSupport.stream(colors.spliterator(), false).
                map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    public ColorDTO getColorByName(String name){
        if(name.isEmpty()){
            return getEmptyDTO();
        }
        return convertEntityToDTO(colorRepo.findFirstByName(name));
    }

    public Iterable<ColorDTO> getAllColors(){
        return convertAllEntityToDTO(colorRepo.findAll());
    }

    @Transactional
    public void saveColor(Color color){
        colorRepo.save(color);
    }

    @Transactional
    public void saveColor(String name){
        Color color = new Color(name);
        colorRepo.save(color);
    }
}
