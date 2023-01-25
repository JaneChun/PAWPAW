package animalsquad.server.domain.pet.controller;

import animalsquad.server.domain.infomap.entity.InfoMapComment;
import animalsquad.server.domain.pet.dto.PetPatchDto;
import animalsquad.server.domain.pet.dto.PetPostAdminDto;
import animalsquad.server.domain.pet.dto.PetPostDto;
import animalsquad.server.domain.pet.entity.Pet;
import animalsquad.server.domain.pet.mapper.PetMapper;
import animalsquad.server.domain.pet.service.PetService;
import animalsquad.server.global.auth.userdetails.PetDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pets")
@Validated
public class PetController {

    private final PetService petService;
    private final PetMapper mapper;

    @PostMapping("/signup")
    public ResponseEntity postPet(@Valid PetPostDto petPostDto) throws IllegalAccessException {
        Pet pet = petService.createPet(mapper.petPostToPet(petPostDto), petPostDto.getProfileImage());
        long id = pet.getId();
        return new ResponseEntity(id, HttpStatus.CREATED);
    }

    @GetMapping("/check/{login-id}")
    public ResponseEntity<Boolean> checkPet(@PathVariable ("login-id") String loginId) {
        Boolean result = petService.checkLoginId(loginId);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping ("/admin/{pet-id}")
    public ResponseEntity verifiedAdmin(@PathVariable("pet-id") long id,
                                        @RequestBody PetPostAdminDto petPostAdminDto,
                                        @AuthenticationPrincipal PetDetailsService.PetDetails principal,
                                        HttpServletResponse response) {
        long petId = principal.getId();

        petService.verifiedAdmin(id, petId, petPostAdminDto, response);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{pet-id}")
    public ResponseEntity patchPet(@PathVariable("pet-id") long id,
                                    PetPatchDto petPatchDto,
                                   @AuthenticationPrincipal PetDetailsService.PetDetails principal) throws IllegalAccessException {
        long petId = principal.getId();

        petPatchDto.setId(id);

        petService.updatePet(mapper.petPatchToPet(petPatchDto), petId ,petPatchDto.getProfileImage());

        return new ResponseEntity(HttpStatus.OK);
    }

    //TODO: post 조회
    @GetMapping("/{pet-id}")
    public ResponseEntity getPet(@PathVariable("pet-id") long id,
                                 @Positive @RequestParam(defaultValue = "1") int page,
                                 @Positive @RequestParam(defaultValue = "15") int size,
                                 @AuthenticationPrincipal PetDetailsService.PetDetails principal) {
        long petId = principal.getId();

        Pet findPet = petService.petVerifiedToken(id, petId);
        Page<Post> posts = petService.findPost(page -1, size, id);

        return new ResponseEntity(mapper.petToPetResponseDto(findPet, posts),HttpStatus.OK);
    }
    @DeleteMapping("/{pet-id}")
    public ResponseEntity deletePet(@PathVariable("pet-id") long id,
                                    @AuthenticationPrincipal PetDetailsService.PetDetails principal) throws IllegalAccessException {
        long petId = principal.getId();

        petService.deletePet(id, petId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
