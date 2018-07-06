package ua.com.proteins.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.com.proteins.dao.ProductDao;
import ua.com.proteins.dto.filter.ProductFilter;
import ua.com.proteins.dto.form.ProductForm;
import ua.com.proteins.entity.Product;
import ua.com.proteins.entity.ProductCategory;
import ua.com.proteins.entity.ProductProducer;
import ua.com.proteins.entity.ProductTaste;
import ua.com.proteins.service.FileWriter;
import ua.com.proteins.service.FileWriter.Folder;
import ua.com.proteins.service.ProductService;
import ua.com.proteins.specification.ProductSpecification;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private FileWriter fileWriter;


	public void save(ProductForm form) {
		Product product = new Product();
		product.setPrice(new BigDecimal(form.getPrice().replace(',', '.')));
		product.setWeight(new BigDecimal(form.getWeight().replace(',', '.')));
		product.setProductDescription(form.getProductDescription());
		product.setProductName(form.getProductName());
		product.setId(form.getId());
		product.setProductProducer(form.getProductProducer());
		product.setProductTaste(form.getProductTaste());
		product.setProductCategory(form.getproductCategory());
		MultipartFile file = form.getFile();
		productDao.saveAndFlush(product);
		if(fileWriter.write(Folder.PRODUCT,file, product.getId())){
			product.setVersion(product.getVersion()+1);
			productDao.save(product);
		}
		

	}

	public List<Product> findAll() {
		
		return productDao.findAll();
	}

	public Product findOne(int id) {

		return productDao.findOne(id);
	}

	public void delete(int id) {
		productDao.delete(id);

	}

	public Product findUnique(String price, String weight, String productDescription,String productName,
			ProductProducer productProducer, ProductTaste productTaste,ProductCategory productCategory) {
		return productDao.findUnique(new BigDecimal(price.replace(',', '.')),new BigDecimal(weight.replace(',', '.')),productDescription,productName, 
				productProducer.getId(), productTaste.getId(), productCategory.getId());
	}

	public ProductForm findForm(int id) {
		ProductForm form = new ProductForm();
		Product product = findOne(id);
		form.setWeight(String.valueOf(product.getWeight()));
		form.setPrice(String.valueOf(product.getPrice()));
		form.setProductDescription(product.getProductDescription());
		form.setProductName(product.getProductName());
		form.setId(product.getId());
		form.setProductProducer(product.getProductProducer());
		form.setProductTaste(product.getProductTaste());
		form.setproductCategory(product.getProductCategory());
		return form;
	}

	@Override
	public Page<Product> findAll(Pageable pageable, ProductFilter filter) {
		return productDao.findAll(new ProductSpecification(filter),pageable);
	}

	@Override
	public Page<Product> findByCategory(int id, Pageable pageable) {
		// TODO Auto-generated method stub
		return productDao.findByPidvudCategoryId(id, pageable);
	}

	@Override
	public void save(Product product) {
		// TODO Auto-generated method stub
		productDao.save(product);
	}
	
	

}
