package org.example.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.entity.CustomerEntity;
import org.example.model.Customer;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    final CustomerRepository repository;
    final ObjectMapper objectMapper;

    @Override
    public void addCustomer(Customer customer) {
        CustomerEntity customerEntity = objectMapper.convertValue(customer, CustomerEntity.class);
        repository.save(customerEntity);
    }

    @Override
    public Customer getCustomerById(Integer id) {
        Optional<CustomerEntity> customerEntity = repository.findById(id);
        return customerEntity.map(entity -> objectMapper.convertValue(entity, Customer.class)).orElse(null);
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<CustomerEntity> entities = repository.findAll();
        return entities.stream().map(entity -> objectMapper.convertValue(entity, Customer.class)).collect(Collectors.toList());
    }

    @Override
    public void updateCustomer(Integer id, Customer customer) {
        Optional<CustomerEntity> optionalEntity = repository.findById(id);
        if (optionalEntity.isPresent()) {
            CustomerEntity customerEntity = optionalEntity.get();
            customerEntity.setName(customer.getName());
            customerEntity.setCity(customer.getCity());
            customerEntity.setContact(customer.getContact());
            repository.save(customerEntity);
        }
    }

    @Override
    public void deleteCustomer(Integer id) {
        repository.deleteById(id);
    }
}
