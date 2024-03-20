package com.backend.PowerUp.services;

import com.backend.PowerUp.entities.UserImage;
import com.backend.PowerUp.repositories.UserImageRepository;
import com.backend.PowerUp.utils.ImageUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserImageService {
    private final UserImageRepository userImageRepository;

    public void uploadUserImage(MultipartFile file) throws IOException {
        userImageRepository.save(UserImage.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        System.out.println("Сохранилось");
    }

//    @Transactional
//    public UserImage getInfoByImageByName(String name) {
//        Optional<UserImage> dataBaseUserImage = userImageRepository.findByName(name);
//
//        return UserImage.builder()
//                .name(dataBaseUserImage.get().getName())
//                .type(dataBaseUserImage.get().getType())
//                .imageData(ImageUtils.decompressImage(dataBaseUserImage.get().getImageData())).build();
//    }
//
//    @Transactional
//    public byte[] getImage(String name) {
//        Optional<UserImage> dataBaseUserImage = userImageRepository.findByName(name);
//
//        return ImageUtils.decompressImage(dataBaseUserImage.get().getImageData());
//
//    }
}
