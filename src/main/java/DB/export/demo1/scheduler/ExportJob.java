package DB.export.demo1.scheduler;

import DB.export.demo1.entity.Table1;
import DB.export.demo1.repository.Table1Repository;
import DB.export.demo1.utils.JsonFileWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExportJob implements Job {

    @Autowired
    private Table1Repository table1Repository;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<Table1> table1List = table1Repository.findAll();
        System.out.println("Employees: " + table1List);
    }
}
