package ${dao_package};

import ${base_package}.lib.dao.BaseDao;
import ${entity_package}.${entity_name};
import org.springframework.stereotype.Repository;


/**
 * ${entity_description}持久类
 *
 * @author : ${author_name} / ${author_email}
 * @version : 1.0
 */
@Repository
public interface ${entity_name}Dao extends BaseDao<${entity_name}> {
}
