package DB.export.demo1.service;

import DB.export.demo1.entity.Table1;
import DB.export.demo1.repository.Table1Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TableUpdateService {

    @Autowired
    private Table1Repository table1Repository;

    private LocalDateTime lastProcessedAt = LocalDateTime.MIN;

    // In-memory map to track the state of each record (keyed by ID)
    private Map<Integer, Table1> recordState = new HashMap<>();

    @Scheduled(fixedRate = 60000) // Every 1 minute
    public void checkForUpdates() {
        // Fetch records updated after the lastProcessedAt
        List<Table1> updatedRecords = table1Repository.findAllByOrderByUpdatedAtDesc();

        // Separate new and modified records
        List<Table1> newRecords = new ArrayList<>();
        List<Table1> modifiedRecords = new ArrayList<>();

        for (Table1 record : updatedRecords) {
            if (!recordState.containsKey(record.getId())) {
                // New record
                newRecords.add(record);
            } else if (!record.equals(recordState.get(record.getId()))) {
                // Modified record
                modifiedRecords.add(record);
            }

            // Update the record state
            recordState.put(record.getId(), record);
        }

        // Update lastProcessedAt to the most recent `updatedAt` value
        if (!updatedRecords.isEmpty()) {
            lastProcessedAt = updatedRecords.get(0).getUpdatedAt();
        }

        // Print results
        if (!newRecords.isEmpty()) {
            System.out.println("New Records: " + newRecords);
        }
        if (!modifiedRecords.isEmpty()) {
            System.out.println("Modified Records: " + modifiedRecords);
        }
        if (newRecords.isEmpty() && modifiedRecords.isEmpty()) {
            System.out.println("No changes detected.");
        }
    }
}
