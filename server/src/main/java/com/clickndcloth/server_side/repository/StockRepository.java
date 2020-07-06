package com.clickndcloth.server_side.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.clickndcloth.server_side.models.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer>{

//	UPDATE `tblproduct` SET prod_quan=prod_quan-$order_count WHERE prod_id=$product_id;
	//@Query(value = "SELECT product.* FROM Product AS product WHERE product.product_ref = ?1", nativeQuery = true)
//	Query query = em.createQuery("UPDATE Employee e SET e.salary = e.salary + :increment "
//            + "WHERE e.dept = :dept");
    @Query(value = "UPDATE Users u set EMAIL_VERIFICATION_STATUS =:emailVerificationStatus where u.USER_ID = :userId",
            nativeQuery = true)
void updateUser(@Param("emailVerificationStatus") boolean emailVerificationStatus, @Param("userId") String userId);

	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Product_available As pa SET pa.quantite = (pa.quantite - :product_sold) WHERE pa.id  =:id", nativeQuery = true)
	void updateQuantity(@Param("product_sold") int product_sold , @Param("id") int id);
}
