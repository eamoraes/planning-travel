package br.com.planningtravel;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.planningtravel.model.Travel;
import br.com.planningtravel.repository.TravelRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TravelRepositoryTest {
	@Autowired
	private TravelRepository travelRepository;
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void createShouldPersistData() {
		Travel travel = new Travel("New York", "www.jpg", null);
		this.travelRepository.save(travel);
		Assertions.assertThat(travel.getId()).isNotNull();
		Assertions.assertThat(travel.getPlace()).isEqualTo("New York");
		Assertions.assertThat(travel.getPicture()).isEqualTo("www.jpg");
	}
	
    @Test
    public void deleteShouldRemoveData() {
    	Travel travel = new Travel("New York", "www.jpg", null);
        this.travelRepository.save(travel);
        travelRepository.delete(travel);
        assertThat(travelRepository.findById(travel.getId())).isEmpty();
    }

    @Test
    public void updateShouldChangeAndPersistData() {
    	Travel travel = new Travel("New York", "www.jpg", null);
        this.travelRepository.save(travel);
        travel.setPlace("New York2");
        travel.setPicture("www.jpg2");
        this.travelRepository.save(travel);
        Optional<Travel> travel2 = this.travelRepository.findById(travel.getId());
        assertThat(travel2.get().getPlace()).isEqualTo("New York2");
        assertThat(travel2.get().getPicture()).isEqualTo("www.jpg2");
    }
}
