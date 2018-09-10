package ${controller_package};

import ${base_package}.lib.entity.ResponseData;
import ${base_package}.lib.search.DataTable;
import ${base_package}.lib.search.SearchResponse;
import ${base_package}.lib.util.StringUtils;
import ${base_package}.lib.controller.BaseController;
import ${entity_package}.${entity_name};
import ${entity_dto_package}.${entity_name}DTO;
import ${service_package}.${entity_name}Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * ${entity_description}控制器
 *
 * @author : ${author_name} / ${author_email}
 * @version : 1.0
 */
@Controller
@RequestMapping("${package_name}/${lower_entity_name}")
public class ${entity_name}Controller extends BaseController {

    /**
     * ${entity_name} service
     */
    @Autowired
    private ${entity_name}Service ${lower_entity_name}Service;


    /**
     * 请求数据列表界面
     *
     * @param model the model
     * @return the string
     * @author : ${author_name} / ${author_create_date}
     */
    @GetMapping
    public String list(Model model){
        //设置页面实体参数(路径，消息，权限前缀)
        addEntityParam("${web_prefix}", "${web_module_name}", model);
        return getPagePath("${web_prefix}", "${web_module_name}", "list");
    }


    /**
     * 查询数据列表
     *
     * @param dataTable the data table
     * @return the map
     * @author : ${author_name} / ${author_create_date}
     */
    @PostMapping("list")
    @ResponseBody
    public Map<String, Object> query(DataTable dataTable){
        Map<String, Object> map = new HashMap<>();
        //根据dataTable查询数据记录
        SearchResponse<${entity_name}> searchResponse = ${lower_entity_name}Service.findAll(dataTable, ${entity_name}.class);
        map.put(PARAM_DRAW, dataTable.getDraw());
        map.put(PARAM_RECORDS_TOTAL, searchResponse.getRecordsTotal());
        map.put(PARAM_RECORDS_FILTERED, searchResponse.getRecordsFiltered());
        map.put(PARAM_DATA, convert(searchResponse.getData()));
        return map;
    }


    /**
    * Entity转DTO
    *
    * @param ${lower_entity_name}s the ${lower_entity_name}s
    * @return the list
    * @author : ${author_name} / ${author_create_date}
    */
    private List<${entity_name}DTO> convert(List<${entity_name}> ${lower_entity_name}s){
        List<${entity_name}DTO> dtos = new ArrayList<>();
        for (${entity_name} ${lower_entity_name} : ${lower_entity_name}s){
        ${entity_name}DTO dto = new ${entity_name}DTO();
            BeanUtils.copyProperties(${lower_entity_name}, dto);
            dtos.add(dto);
        }
        return dtos;
    }


    /**
    *  新增
    *
    * @param model the model
    * @return the string
    * @author : ${author_name} / ${author_create_date}
    */
    @GetMapping("create")
    public String create(Model model){
        ${entity_name}DTO dto = new ${entity_name}DTO();

        //设置dto对象到页面
        model.addAttribute(PARAM_DTO, dto);
        //设置页面实体参数(路径，消息，权限前缀)
        addEntityParam("${web_prefix}", "${web_module_name}", model);
        return getPagePath("${web_prefix}", "${web_module_name}", "edit");
    }


    /**
    * 更新
    *
    * @param model the model
    * @param id    the id
    * @return the string
    * @author : ${author_name} / ${author_create_date}
    */
    @GetMapping("update")
    public String update(Model model, @RequestParam("id") String id){

        ${entity_name}DTO dto = new ${entity_name}DTO();
        ${entity_name} ${lower_entity_name} = ${lower_entity_name}Service.findById(id);
        BeanUtils.copyProperties(${lower_entity_name}, dto);

        //设置dto对象到页面
        model.addAttribute(PARAM_DTO, dto);
        //设置页面实体参数(路径，消息，权限前缀)
        addEntityParam("${web_prefix}", "${web_module_name}", model);
        return getPagePath("${web_prefix}", "${web_module_name}", "edit");
    }


    /**
    * 删除
    *
    * @param ids the ids
    * @return the response data
    * @author : ${author_name} / ${author_create_date}
    */
    @PostMapping("delete")
    @ResponseBody
    public ResponseData delete(String ids){
        //根据id数组查询对象列表
        Iterable<${entity_name}> list = ${lower_entity_name}Service.findAll(Arrays.asList(StringUtils.split(ids, ",")));
        //删除对象列表
        ${lower_entity_name}Service.deleteInBatch(list);
        return ResponseData.success();
    }


    /**
    * 保存
    *
    *
    * @param dto the dto
    * @return the response data
    * @author : ${author_name} / ${author_create_date}
    */
    @PostMapping("save")
    @ResponseBody
    public ResponseData save(${entity_name}DTO dto){
        ${entity_name} ${lower_entity_name} = new ${entity_name}();
        BeanUtils.copyProperties(dto, ${lower_entity_name});

        ${lower_entity_name}Service.save(${lower_entity_name});

        return ResponseData.success();
    }
}
