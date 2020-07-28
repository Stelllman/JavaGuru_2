package shoppingList.repository;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shoppingList.domain.ProductEntity;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("mysql_Hibernate")
@Transactional
public class HibernateProductImpRepository implements ProductRepository {

    private final SessionFactory sessionFactory;

    HibernateProductImpRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ProductEntity addProduct(ProductEntity newProduct) {
        sessionFactory.getCurrentSession().save(newProduct);
        return newProduct;
    }

    @Override
    public boolean removeProductByID(Long id) {
        Optional<ProductEntity> productEntity = findProductByID(id);
        if (productEntity.isPresent()) {
            sessionFactory.getCurrentSession().delete(productEntity.get());
            return true;
        } else {
            return false;
        }
    }

    @Override//ok - createria ubrat - ustatrevaet//a esli pusto ?
    public List<ProductEntity> listOfAllProducts() {
        return sessionFactory.getCurrentSession().createCriteria(ProductEntity.class)
                .list();
    }

    @Override
    public Optional<ProductEntity> findProductByID(Long id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().find(ProductEntity.class, id));
/*        ProductEntity entity = (ProductEntity) sessionFactory.getCurrentSession().createCriteria(ProductEntity.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return Optional.ofNullable(entity);*/
    }

    @Override
    public ProductEntity updateProduct(Long id, ProductEntity updatedProduct) {
        sessionFactory.getCurrentSession().update(updatedProduct);
        return updatedProduct;
    }

    @Override//ok - create cretaria (proverka save product)
    public Optional<ProductEntity> findProductByName(String name) {
        ProductEntity productEntity = (ProductEntity) sessionFactory.getCurrentSession().createCriteria(ProductEntity.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        return Optional.ofNullable(productEntity);
    }

    @Override
    public boolean existsById(Long id) {
        Optional<ProductEntity> productEntity = Optional.ofNullable(sessionFactory.getCurrentSession().find(ProductEntity.class, id));
        return productEntity.isPresent();
    }
}
