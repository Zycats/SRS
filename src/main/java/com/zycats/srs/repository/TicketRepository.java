package com.zycats.srs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.zycats.srs.entity.Employee;
import com.zycats.srs.entity.Status;
import com.zycats.srs.entity.Ticket;

public interface TicketRepository extends PagingAndSortingRepository<Ticket, Integer> {
	
	
	// --------------- Engineer tickets -------------------//
	@Query(value = "SELECT t from Ticket t  WHERE t.engineer = :engineer")
	List<Ticket> findAllTicketsByEngineer(@Param("engineer") Employee engineer);

	@Query(value = "SELECT t FROM Ticket t WHERE t.status = :enumStatus AND t.engineer = :engineer")
	List<Ticket> findAllTicketsByStatusAndEngineer(@Param("enumStatus") Status status,
			@Param("engineer") Employee engineer);

	@Query(
			value = "SELECT t from Ticket t INNER JOIN IssueSubCategory c ON t.subCategory.id = c.id WHERE c.issueCategory.id = :category_id and t.engineer = :engineer")
	List<Ticket> findAllTicketsByCategoryEngineer(@Param("category_id") int category_id,
			@Param("engineer") Employee engineer);

	@Query(value = "SELECT t from Ticket t  WHERE t.subCategory.id = :sub_category_id AND t.engineer = :engineer")
	List<Ticket> findAllTicketsBySubCategoryEngineer(@Param("sub_category_id") int sub_category_id,
			@Param("engineer") Employee engineer);

	@Query(
			value = "SELECT t from Ticket t INNER JOIN IssueSubCategory c ON t.subCategory.id = c.id WHERE c.issueCategory.id = :category_id")
	Iterable<Ticket> findAllTicketsByCategory(@Param("category_id") int category_id);

	@Query(value = "SELECT t from Ticket t  WHERE t.subCategory.id = :sub_category_id")
	List<Ticket> findAllTicketsBySubCategory(@Param("sub_category_id") int sub_category_id);

	@Query(
			value = "SELECT t from Ticket t INNER JOIN IssueSubCategory c ON t.subCategory.id = c.id WHERE c.issueCategory.id = :category_id AND t.status = :status")
	Iterable<Ticket> findAllTicketsByCategoryStatus(@Param("category_id") int category_id,
			@Param("status") Status status);

	@Query(value = "SELECT t from Ticket t  WHERE t.subCategory.id = :sub_category_id  AND t.status = :status")
	List<Ticket> findAllTicketsBySubCategoryStatus(@Param("sub_category_id") int sub_category_id,
			@Param("status") Status status);

	@Query(value = "SELECT t FROM Ticket t where t.status = :status")
	Iterable<Ticket> findAllTicketsByStatus(@Param("status") Status status);

	// ---------------- Employee Tickets --------------------//

	@Query(value = "SELECT t from Ticket t  WHERE t.employee = :employee")
	List<Ticket> findAllTicketsByEmployee(@Param("employee") Employee employee);
	
	@Query(value = "SELECT t FROM Ticket t WHERE t.status = :enumStatus AND t.employee = :employee")
	List<Ticket> findAllTicketsByStatusAndEmployee(@Param("enumStatus") Status status,
			@Param("employee") Employee employee);
	
	@Query(
			value = "SELECT t from Ticket t INNER JOIN IssueSubCategory c ON t.subCategory.id = c.id WHERE c.issueCategory.id = :category_id and t.employee = :employee")
	List<Ticket> findAllTicketsByCategoryEmployee(@Param("category_id") int category_id, @Param("employee") Employee employee);
	
	@Query(value = "SELECT t from Ticket t  WHERE t.subCategory.id = :sub_category_id AND t.employee = :employee")
	List<Ticket> findAllTicketsBySubCategoryEmployee(@Param("sub_category_id") int sub_category_id,
			@Param("employee") Employee employee);
	
	
//-------------------------------------------------COUNTS ---------------------------------------------------////	
	
	// -- ticket count queries ----------///
	@Query(value = "SELECT COUNT(t.id) FROM Ticket t")
	Object getNoOfTickets();
	
		//---- Executive Related Queries----------//////
	@Query(value = "SELECT COUNT(t.id) FROM Ticket t where t.status = :enumStatus and t.engineer = :engineer")
	Object getNoOfTicketsByStatusEngineer(@Param("enumStatus") Status status,
			@Param("engineer") Employee engineer);
	
	//---- Employee Related Queries----------//////
	
	@Query(value = "SELECT COUNT(t.id) FROM Ticket t WHERE t.employee = :employee")
	Object getNoOfTicketsByEmployee(@Param("employee") Employee employee);
	
	@Query(value = "SELECT COUNT(t.id) FROM Ticket t where t.status = :enumStatus and t.employee = :employee")
	Object getNoOfTicketsByStatusEmployee(@Param("enumStatus") Status status,
			@Param("employee") Employee employee);
	
}
