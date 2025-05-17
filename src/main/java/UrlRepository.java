import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

// UrlRepository.java
@ApplicationScoped
public class UrlRepository implements PanacheRepository<UrlMapping> {

    @Transactional
    public UrlMapping findByShortCode(String shortCode) {
        return UrlMapping.find("shortCode", shortCode).firstResult();
    }

    @Transactional
    public void update(UrlMapping mapping) {
        mapping.persist();
    }

    @Transactional
    public void save(UrlMapping mapping) {
        mapping.persist();
    }
}