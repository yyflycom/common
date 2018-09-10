package ${service_impl_package};

import ${base_package}.lib.dao.BaseDao;
import ${base_package}.lib.service.impl.BaseServiceImpl;
import ${entity_package}.${entity_name};
import ${dao_package}.${entity_name}Dao;
import ${service_package}.${entity_name}Service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * ${entity_description}业务接口实现类
 *
 * @author : ${author_name} / ${author_email}
 * @version : 1.0
 */
@Service
public class ${entity_name}ServiceImpl extends BaseServiceImpl<${entity_name}> implements ${entity_name}Service {

    @Autowired
    private ${entity_name}Dao ${lower_entity_name}Dao;


    @Override
    protected BaseDao<${entity_name}> getDao() {
        return ${lower_entity_name}Dao;
    }
}
