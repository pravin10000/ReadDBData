package DB.export.demo1.service;

import DB.export.demo1.entity.Table1;
import DB.export.demo1.repository.Table1Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class TableUpdateService {

    @Autowired
    private Table1Repository table1Repository;

    private List<Table1> previousData;
    @Autowired
    private ObjectMapper objectMapper;

    private LocalDateTime lastProcessedAt = null;

    @Scheduled(fixedRate = 60000)
    public void checkForChanges() throws JsonProcessingException {
        try {
            List<Table1> dataToProcess;

            if (lastProcessedAt == null) {
                // First run: Fetch all data
                System.out.println("Fetching all data...");
                dataToProcess = table1Repository.findAllByOrderByUpdatedAtDesc();
            } else {
                // Subsequent runs: Fetch only updated or newly inserted data
                System.out.println("Fetching data updated after: " + lastProcessedAt);
                dataToProcess = table1Repository.findUpdatedData(lastProcessedAt);
            }

            if (dataToProcess.isEmpty()) {
                System.out.println("No new changes detected.");
            } else {
                // Print data in JSON format
                String json = objectMapper.writeValueAsString(dataToProcess);
                System.out.println("Updated data: " + json);

                // Update lastProcessedAt to the latest `updated_at` value
                lastProcessedAt = dataToProcess.get(0).getUpdatedAt();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
