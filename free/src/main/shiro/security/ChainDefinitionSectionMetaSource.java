package shiro.security;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import shiro.model.sys.SysMenu;
import shiro.service.SysMenuIService;


/**
 * 借助spring {@link org.springframework.beans.factory.FactoryBean} 对apache shiro的premission进行动态创建
 * User: liguo
 * Date: 13-9-26
 * Time: 下午3:40
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("rawtypes")
public class ChainDefinitionSectionMetaSource implements FactoryBean {

    protected static Logger log = LoggerFactory.getLogger(ChainDefinitionSectionMetaSource.class);

    private static Map<String,String> fixedAuthRuleMap=new LinkedHashMap<String, String>();

    @Autowired
    private SysMenuIService sysMenuIService;

    /**
     * 默认premission字符串
     */
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    //shiro默认的链接定义
    private String filterChainDefinitions;

    public Section getObject() throws BeansException {
        //List<SysMenu> menuList = sysMenuIService.findALLFlatSysMenu();
        List<SysMenu> menuList = sysMenuIService.findAll();

        Ini ini = new Ini();
        // 加载默认的url
        ini.load(filterChainDefinitions);
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);

        // 保存默认的url
        fixedAuthRuleMap=new LinkedHashMap<String, String>();
        Set<Map.Entry<String, String>>  entrySet=section.entrySet();
        for(Map.Entry<String, String> entry:entrySet){
            fixedAuthRuleMap.put(entry.getKey(),entry.getValue());
        }

        for (SysMenu menu : menuList) {
            if (StringUtils.isNotEmpty(menu.getForward()) && StringUtils.isNotEmpty(menu.getPermissionCode())) {
                section.put(menu.getForward(), MessageFormat.format(PREMISSION_STRING, menu.getPermissionCode()));
            }
        }

        //section.put("/privOperateLogAction.do?method=list", MessageFormat.format(PREMISSION_STRING,"admin"));

        section.put("/**", "authc");//anon

        return section;
    }

    /**
     * 通过filterChainDefinitions对默认的链接过滤定义
     *
     * @param filterChainDefinitions 默认的接过滤定义
     */
    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    public Class<?> getObjectType() {
        return this.getClass();
    }

    public boolean isSingleton() {
        return false;
    }


    /**
     * 取得固定的URL过滤规则
     * @return
     */
    public static Map<String,String> getFixedAuthRuleMap(){
        return fixedAuthRuleMap;
    }

}
