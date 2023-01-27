package net.codejava.project;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.codejava.document.Document;
 
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
	public Project findAllByProjectid(Long projectid);
}
