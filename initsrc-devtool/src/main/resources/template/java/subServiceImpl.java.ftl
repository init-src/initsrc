package ${packageName}.${moduleName}.service.impl;

import ${packageName}.${moduleName}.entity.${bizName}.${className};
import ${packageName}.${moduleName}.service.${className}Service;
import ${packageName}.${moduleName}.dao.${className}Mapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
/**
* <p>
    * ${functionName}-服务实现类
    * </p>
*
* @author ${genAuthor}
* @since  ${date?string("yyyy-MM-dd HH:mm:ss")}
*/
@Service
public class ${className}ServiceImpl extends ServiceImpl<${className}Mapper, ${className}> implements ${className}Service {

}
