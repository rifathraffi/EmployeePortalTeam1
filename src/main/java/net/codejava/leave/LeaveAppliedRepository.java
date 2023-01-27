package net.codejava.leave;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.codejava.employee.Employee;

import java.util.List;
import java.util.Optional;
 
@Repository
public interface LeaveAppliedRepository extends JpaRepository<LeaveApplied, Long> {
	public List<LeaveApplied> findAllByEmpid(Long empid);
	public LeaveApplied findAllById(Long id);
}