package org.demo.app.db.jpa.commons;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;

/**
 * Abstract class for mapping in services <br>
 * 
 * @author xxx
 *
 * @param <ENTITY>
 * @param <STDOBJ>
 */
public abstract class GenericService<ENTITY, STDOBJ> {

	private final ModelMapper mapper = new ModelMapper();

	private final Class<ENTITY> entityClass;
	private final Class<STDOBJ> dtoClass;

	/**
	 * Constructor
	 * 
	 * @param entityClass
	 * @param dtoClass
	 */
	protected GenericService(Class<ENTITY> entityClass, Class<STDOBJ> dtoClass) {
		super();
		this.entityClass = entityClass;
		this.dtoClass = dtoClass;
	}

	/**
	 * Converts STDOBJ to JPA ENTITY
	 * 
	 * @param dto
	 * @return
	 */
	protected ENTITY dtoToEntity(STDOBJ dto) {
		return mapper.map(dto, entityClass);
		// return BookMapper.getInstance().dtoToEntity(dto);
	}

	/**
	 * Converts JPA ENTITY to STDOBJ
	 * 
	 * @param entity
	 * @return
	 */
	protected STDOBJ entityToDto(ENTITY entity) {
		return mapper.map(entity, dtoClass);
		// return BookMapper.getInstance().entityToDto(entity);
	}

	/**
	 * Converts an Optional JPA ENTITY to STDOBJ
	 * 
	 * @param optionalEntity
	 * @return the STDOBJ or null if nothing in optional
	 */
	protected STDOBJ entityToDto(Optional<ENTITY> optionalEntity) {
		if (optionalEntity.isPresent()) {
			return entityToDto(optionalEntity.get());
		} else {
			return null;
		}
	}

	/**
	 * Converts a collection of JPA ENTITIES to a collection of STDOBJ
	 * 
	 * @param entities
	 * @return
	 */
	protected List<STDOBJ> entityListToDtoList(Iterable<ENTITY> entities) {
		List<STDOBJ> dtoList = new LinkedList<>();
		if (entities != null) {
			for (ENTITY e : entities) {
				dtoList.add(entityToDto(e));
			}
		}
		return dtoList;
	}
}
