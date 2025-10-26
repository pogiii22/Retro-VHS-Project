package com.example.demo;

import com.example.demo.dao.RentalRepository;
import com.example.demo.domain.Rental;
import com.example.demo.domain.User;
import com.example.demo.domain.VHS;
import com.example.demo.rest.RentalDTO;
import com.example.demo.service.impl.RentalServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.service.impl.VHSServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateRentalTest {

    @Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private RentalServiceImpl rentalService;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private VHSServiceImpl vhsService;

    @Test
    void ListAll_returnsListOfRentals(){
        Rental r1 = new Rental();
        Rental r2 = new Rental();
        List<Rental> rentals = List.of(r1, r2);

        when(rentalRepository.findAll()).thenReturn(rentals);

        List<Rental> result = rentalService.listAll();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(r1));
        assertTrue(result.contains(r2));
    }

    @Test
    void shouldCreateNewRentalWhenVhsIsAvailable() {
        VHS vhs = new VHS(1L,"Riot", "war", 1998, false);
        User user = new User("Test", "test@example.com");

        RentalDTO dto = new RentalDTO(vhs.getTitle(), user.getEmail());

        when(userService.findByEmail(anyString())).thenReturn(user);
        when(vhsService.findFirstByTitleAndRentedFalse(vhs.getTitle())).thenReturn(vhs);
        doNothing().when(vhsService).saveVHS(any(VHS.class));

        when(rentalRepository.save(any(Rental.class))).thenAnswer(inv -> {
            Rental r = inv.getArgument(0);
            r.setId(1L); //set up ID of rental
            return r;
        });

        Rental rental = rentalService.createRental(dto);
        assertNotNull(rental);
        assertEquals(vhs, rental.getVhs());
        assertEquals(user, rental.getUser());
        assertTrue(vhs.getRented());
    }
}

