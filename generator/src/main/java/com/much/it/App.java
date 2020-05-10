package com.much.it;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @project: much
 * @date: 2020/5/9
 * @author: Wenxin
 * @version: 1.0
 */
@SpringBootApplication
//@ComponentScan(basePackages = {"com.much.it.service","com.much.it.controller"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
        //generatorCode();
    }

    public static void generatorCode(){
        String projectName = "generator";
        String url = "jdbc:mysql://localhost:3306/much?characterEncoding=utf8&allowMulitQueries=ture&useSSL=false&serverTimezone=UTC";
        String dirverName = "com.mysql.cj.jdbc.Driver";
        String parentPackage = "com.much.it";
        String[] tables = new String[]{""};
        GlobalConfig globalConfig = getGlobalConfig(projectName);
        DataSourceConfig dataSourceConfig = getDatasourceConfig(url,dirverName);
        StrategyConfig strategyConfig = getStrategyConfig(tables);
        PackageConfig packageConfig = getPackageConfig(parentPackage);
        new AutoGenerator().setGlobalConfig(globalConfig).setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig).setPackageInfo(packageConfig).execute();
    }

    private static GlobalConfig getGlobalConfig(String projectName ) {
        String path = App.class.getClassLoader().getResource("").getPath();
        int generator = path.lastIndexOf(projectName);
        String driPath = path.substring(1,generator)+"/"+projectName+"/src/main/java";
        return new GlobalConfig().setActiveRecord(true).setAuthor("zhangruting").setBaseColumnList(true)
                .setBaseResultMap(true).setFileOverride(false).setServiceName("%sService").setOutputDir(driPath);
    }

    private static DataSourceConfig getDatasourceConfig(String url,String dirverName) {

        return new DataSourceConfig().setDbType(DbType.MYSQL).setUrl(url).setUsername("root").setPassword("root").setDriverName(dirverName);
    }

    private static StrategyConfig getStrategyConfig( String[] tables) {
        return new StrategyConfig().setCapitalMode(true).setNaming(NamingStrategy.underline_to_camel).setDbColumnUnderline(false)
                .setEntityBuilderModel(true).setEntityLombokModel(true).setRestControllerStyle(true)
                .setExclude(tables);
    }

    private static PackageConfig getPackageConfig( String parentPackage) {
        return new PackageConfig().setController("controller").setParent(parentPackage);
    }
}
