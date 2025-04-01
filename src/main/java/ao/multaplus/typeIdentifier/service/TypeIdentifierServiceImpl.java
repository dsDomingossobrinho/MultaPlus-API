package ao.multaplus.typeIdentifier.service;

import ao.multaplus.typeIdentifier.dtos.TipeIdentifierDto;
import ao.multaplus.typeIdentifier.dtos.TipeidentifierSaveDTO;
import ao.multaplus.typeIdentifier.entity.TypeIdentifiers;
import ao.multaplus.typeIdentifier.repository.TypeIdentifierRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TypeIdentifierServiceImpl implements TypeIdentifierService{
    private final TypeIdentifierRepository repository;
    public TypeIdentifierServiceImpl(TypeIdentifierRepository repository){
        this.repository=repository;
    }

    @Override
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @Override
    public TipeIdentifierDto findone(long id) {
        return repository.findById(id);
    }

    @Override
    public ResponseEntity<?> save(TipeidentifierSaveDTO saveDTO) {
        TypeIdentifiers identifiers=new TypeIdentifiers();
        identifiers.setType(saveDTO.type());
        identifiers.setDescription(saveDTO.description());
        repository.save(identifiers);
        return new ResponseEntity<>(identifiers,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> update(long id, TipeidentifierSaveDTO saveDTO) {
        TipeIdentifierDto tipeIdentifierDto=repository.findById(id);
        if (tipeIdentifierDto== null){
            return new ResponseEntity<>("Type Identifier not found",HttpStatus.NOT_FOUND);
        }
        TypeIdentifiers identifiers=new TypeIdentifiers();
        identifiers.setId(tipeIdentifierDto.id());
        identifiers.setType(tipeIdentifierDto.type());
        identifiers.setDescription(tipeIdentifierDto.description());

        identifiers.setType(saveDTO.type());
        identifiers.setDescription(saveDTO.description());
        identifiers.setId(id);
        repository.save(identifiers);
        return new ResponseEntity<>(identifiers,HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> delete(long id) {
        TipeIdentifierDto typeIdentifiers=repository.findById(id);
        if (typeIdentifiers==null){
            return new ResponseEntity<>("Not found",HttpStatus.OK);
        }
        TypeIdentifiers identifiers=new TypeIdentifiers();
        identifiers.setId(id);
        identifiers.setType(typeIdentifiers.type());
        identifiers.setDescription(typeIdentifiers.description());
        repository.delete(identifiers);
        return new ResponseEntity<>("Deleted with success",HttpStatus.OK);
    }
}
