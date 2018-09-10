package ${entity_package};

import ${base_package}.lib.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ${entity_description}
 *
 * @author : ${author_name} / ${author_email}
 * @version : 1.0
 */
@Data
@Entity
@Table(name = "${table_name}")
public class ${entity_name} extends BaseEntity {
}
