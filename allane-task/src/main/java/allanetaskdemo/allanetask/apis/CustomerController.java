package allanetaskdemo.allanetask.apis;

import allanetaskdemo.allanetask.dtos.CustomerDTO;
import allanetaskdemo.allanetask.entities.Customer;
import allanetaskdemo.allanetask.repositories.CustomerRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;

    @GetMapping
    @ResponseBody
    Page<CustomerDTO> getCustomers(@RequestParam(defaultValue = "0") Integer page,
                                   @RequestParam(defaultValue = "10") Integer size) {
        var pageable = PageRequest.of(page, size,  Sort.Direction.ASC, "id");
        return this.customerRepository.findCustomers(pageable);
    }

    @GetMapping("/{id}")
    @ResponseBody
    CustomerDTO getCustomer(@PathVariable("id") @NotNull Long id) {
        return this.customerRepository.getCustomerDetails(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseBody
    CustomerDTO createCustomer(@RequestBody @NotNull @Valid CustomerDTO customerDto) {
        var customer = this.mapFromDto(customerDto, new Customer());
        customer = this.customerRepository.save(customer);
        return this.getCustomer(customer.getId());
    }

    @PutMapping("/{id}")
    @ResponseBody
    CustomerDTO updateCustomer(@PathVariable("id") @NotNull Long id,
                               @RequestBody @NotNull @Valid CustomerDTO customerDto) {
        var customer = this.customerRepository.findByIdAndVersion(id, customerDto.getVersion())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT));
        this.customerRepository.save(mapFromDto(customerDto, customer));
        return this.getCustomer(id);
    }

    private Customer mapFromDto(CustomerDTO dto, Customer customer) {
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setBirthDate(dto.getBirthDate());
        return customer;
    }
}
