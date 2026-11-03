package cl.koz.receta_medica.assemblers;

import cl.koz.receta_medica.DTO.RecetaMedicaResponseDTO;
import cl.koz.receta_medica.controller.RecetaMedicaController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RecetaModelAssembler implements RepresentationModelAssembler<RecetaMedicaResponseDTO, EntityModel<RecetaMedicaResponseDTO>> {
    @Override
    public EntityModel<RecetaMedicaResponseDTO> toModel(RecetaMedicaResponseDTO receta){
        return EntityModel.of(receta,
                linkTo(methodOn(RecetaMedicaController.class).obtenerPorNumReceta(receta.getNumReceta())).withSelfRel(),
                linkTo(methodOn(RecetaMedicaController.class).obtenerTodoRM()).withRel("todas-las-recetas")
        );
    }
}
