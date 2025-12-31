package com.esrayilmaz.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esrayilmaz.dto.CurrencyRatesResponse;
import com.esrayilmaz.dto.car.DtoCar;
import com.esrayilmaz.dto.customer.DtoCustomer;
import com.esrayilmaz.dto.gallerist.DtoGallerist;
import com.esrayilmaz.dto.saledcar.DtoSaledCar;
import com.esrayilmaz.dto.saledcar.DtoSaledCarIU;
import com.esrayilmaz.enums.CarStatusType;
import com.esrayilmaz.exception.BaseException;
import com.esrayilmaz.exception.ErrorMessage;
import com.esrayilmaz.exception.MessageType;
import com.esrayilmaz.model.Car;
import com.esrayilmaz.model.Customer;
import com.esrayilmaz.model.SaledCar;
import com.esrayilmaz.repository.CarRepository;
import com.esrayilmaz.repository.CustomerRepository;
import com.esrayilmaz.repository.GalleristRepository;
import com.esrayilmaz.repository.SaledCarRepository;
import com.esrayilmaz.service.ICurrencyRatesService;
import com.esrayilmaz.service.ISaledCarService;
import com.esrayilmaz.utils.DateUtils;

@Service
public class SaledCarService implements ISaledCarService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private SaledCarRepository saledCarRepository;
	
	@Autowired
	private ICurrencyRatesService currencyRatesService;
	
	
	@Override
	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {
		if(!checkCarStatus(dtoSaledCarIU.getCarId())) {
			throw new BaseException(new ErrorMessage(MessageType.CAR_IS_ALREADY_SALED, dtoSaledCarIU.getCarId().toString()));
		}
	
		if(!checkAmount(dtoSaledCarIU)) {
			throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, ""));
		}
		
		SaledCar saledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));
		
		Car car = saledCar.getCar();
		car.setCarStatusType(CarStatusType.SALED);
		carRepository.save(car);
		
		Customer customer = saledCar.getCustomer();
		customer.getAccount().setAmount(remainingCustomerAmount(customer, car));
		customerRepository.save(customer);
		
		return toDTO(saledCar);
	}

	private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
		SaledCar saledCar = new SaledCar();
		saledCar.setCreatedTime(new Date());
		
		saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
		saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
		saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));
		
		return saledCar;
	}
	
	private DtoSaledCar toDTO(SaledCar saledCar) {
		DtoSaledCar dtoSaledCar = new DtoSaledCar();
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoCar dtoCar = new DtoCar();
		
		BeanUtils.copyProperties(saledCar, dtoSaledCar);
		BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
		BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(saledCar.getCar(), dtoCar);
		
		BeanUtils.copyProperties(saledCar.getCustomer().getAccount(), dtoCustomer.getAccount());
		BeanUtils.copyProperties(saledCar.getCustomer().getAddress(), dtoCustomer.getAddress());
		BeanUtils.copyProperties(saledCar.getGallerist().getAddress(), dtoGallerist.getAddress());
			
		return dtoSaledCar;
	}
	
	private boolean checkCarStatus(Long carId) {
		Optional<Car> optCar = carRepository.findById(carId);
		
		if(optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {
			return false;
		}
		
		return true;
	}
	
	private boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {
		Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
		if(optCustomer.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId().toString()));
		}
		
		Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
		if(optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString()));
		}
		
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());
		if(customerUSDAmount.compareTo(optCar.get().getPrice()) == 0 || customerUSDAmount.compareTo(optCar.get().getPrice()) > 0) {
			return true;
		}
		
		return false;
	}
	
	private BigDecimal convertCustomerAmountToUSD(Customer customer) {
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date())); 
		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
		
		BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);
		return customerUSDAmount;                                                                                                                                            
	}
	
	private BigDecimal remainingCustomerAmount(Customer customer, Car car) {
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
		BigDecimal remainingCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());
		
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date())); 
		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
		
		return remainingCustomerUSDAmount.multiply(usd);
	}
	
}
