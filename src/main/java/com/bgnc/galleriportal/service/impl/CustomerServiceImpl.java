package com.bgnc.galleriportal.service.impl;

import com.bgnc.galleriportal.dto.AccountResponse;
import com.bgnc.galleriportal.dto.AddressResponse;
import com.bgnc.galleriportal.dto.CustomerRequest;
import com.bgnc.galleriportal.dto.CustomerResponse;
import com.bgnc.galleriportal.exception.BaseException;
import com.bgnc.galleriportal.exception.ErrorMessage;
import com.bgnc.galleriportal.exception.MessageType;
import com.bgnc.galleriportal.model.Account;
import com.bgnc.galleriportal.model.Address;
import com.bgnc.galleriportal.model.Customer;
import com.bgnc.galleriportal.repository.AccountRepository;
import com.bgnc.galleriportal.repository.AddressRepository;
import com.bgnc.galleriportal.repository.CustomerRepository;
import com.bgnc.galleriportal.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final AccountRepository accountRepository;


    private Customer createCustomer(CustomerRequest customerRequest){

        Optional<Address> optAddress = addressRepository.findById(customerRequest.getAddress_id());



        if(optAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXISTS,customerRequest.getAddress_id().toString()));
        }
        Optional<Account> optAccount = accountRepository.findById(customerRequest.getAccount_id());

        if(optAccount.isEmpty())
        {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXISTS,customerRequest.getAccount_id().toString()));
        }

        Customer customer = new Customer();
        customer.setCreateTime(new Date());
        BeanUtils.copyProperties(customerRequest, customer);

        customer.setAddress(optAddress.get());
        customer.setAccount(optAccount.get());


        return customer;
    }

    @Override
    public CustomerResponse saveCustomer(CustomerRequest customerRequest) {


        CustomerResponse customerResponse = new CustomerResponse();
        AccountResponse accountResponse = new AccountResponse();
        AddressResponse addressResponse = new AddressResponse();


        Customer savedCustomer = customerRepository.save(createCustomer(customerRequest));


        BeanUtils.copyProperties(savedCustomer, customerResponse);
        BeanUtils.copyProperties(savedCustomer.getAccount(), accountResponse);
        BeanUtils.copyProperties(savedCustomer.getAddress(), addressResponse);

        customerResponse.setAccount(accountResponse);
        customerResponse.setAddress(addressResponse);

        return customerResponse;
    }
}
