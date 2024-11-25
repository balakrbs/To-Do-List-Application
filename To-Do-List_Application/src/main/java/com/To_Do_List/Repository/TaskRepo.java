package com.To_Do_List.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.To_Do_List.Model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {
}
