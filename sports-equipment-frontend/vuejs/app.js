// API Base URL
const API_BASE_URL = 'http://localhost:8080/api';

// Create Vue App
const { createApp } = Vue;

createApp({
    data() {
        return {
            brands: []
        };
    },
    
    // Load data when app starts
    mounted() {
        this.loadBrandsAndProducts();
    },
    
    methods: {
 
        async loadBrandsAndProducts() {
            try {
                const response = await fetch(`${API_BASE_URL}/brands`);
                const brands = await response.json();
                
                // Get products for each brand
                for (const brand of brands) {
                    const productsResponse = await fetch(`${API_BASE_URL}/products/brand/${brand.id}`);
                    brand.products = await productsResponse.json();
                }
                
                this.brands = brands;
            } catch (error) {
                console.error('Error:', error);
                alert('Error! Make sure your API is running!');
            }
        },
        
        async addBrand() {
            const name = prompt('Brand name:');
            if (!name) return;
            
            const year = prompt('Founding year (optional):');
            const website = prompt('Website (optional):');
            const description = prompt('Description (optional):');
            
            try {
                await fetch(`${API_BASE_URL}/brands`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        name: name,
                        foundingYear: year ? parseInt(year) : null,
                        website: website || null,
                        description: description || null,
                        isActive: true
                    })
                });
                alert('Brand created!');
                this.loadBrandsAndProducts();
            } catch (error) {
                console.error('Error:', error);
                alert('Error creating brand!');
            }
        },
        
        async editBrand(brand) {
            const name = prompt('Brand name:', brand.name);
            if (!name) return;
            
            const year = prompt('Founding year:', brand.foundingYear);
            const website = prompt('Website:', brand.website);
            const description = prompt('Description:', brand.description);
            
            try {
                await fetch(`${API_BASE_URL}/brands/${brand.id}`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        name: name,
                        foundingYear: year ? parseInt(year) : null,
                        website: website || null,
                        description: description || null,
                        isActive: brand.isActive
                    })
                });
                alert('Brand updated!');
                this.loadBrandsAndProducts();
            } catch (error) {
                console.error('Error:', error);
                alert('Error updating brand!');
            }
        },
        
        async deleteBrand(id) {
            if (!confirm('Delete this brand? This will also delete all its products!')) return;
            
            try {
                await fetch(`${API_BASE_URL}/brands/${id}`, {
                    method: 'DELETE'
                });
                alert('Brand deleted!');
                this.loadBrandsAndProducts();
            } catch (error) {
                console.error('Error:', error);
                alert('Error deleting brand!');
            }
        },
        
        async addProduct() {
            if (this.brands.length === 0) {
                alert('Please create a brand first!');
                return;
            }
            
            let brandsList = 'Available brands:\n';
            this.brands.forEach(b => {
                brandsList += `${b.id}: ${b.name}\n`;
            });
            
            const brandId = prompt(brandsList + '\nEnter brand ID:');
            if (!brandId) return;
            
            const name = prompt('Product name:');
            if (!name) return;
            
            const sku = prompt('SKU:');
            if (!sku) return;
            
            const price = prompt('Price:');
            if (!price) return;
            
            const sportCategory = prompt('Sport category:');
            if (!sportCategory) return;
            
            const productType = prompt('Product type:');
            if (!productType) return;
            
            const stockQuantity = prompt('Stock quantity (optional):');
            
            try {
                await fetch(`${API_BASE_URL}/products`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        brandId: parseInt(brandId),
                        name: name,
                        sku: sku,
                        price: parseFloat(price),
                        sportCategory: sportCategory,
                        productType: productType,
                        stockQuantity: stockQuantity ? parseInt(stockQuantity) : 0,
                        isAvailable: true
                    })
                });
                alert('Product created!');
                this.loadBrandsAndProducts();
            } catch (error) {
                console.error('Error:', error);
                alert('Error creating product!');
            }
        },
        
        async editProduct(product) {
            const name = prompt('Product name:', product.name);
            if (!name) return;
            
            const sku = prompt('SKU:', product.sku);
            const price = prompt('Price:', product.price);
            const sportCategory = prompt('Sport category:', product.sportCategory);
            const productType = prompt('Product type:', product.productType);
            const stockQuantity = prompt('Stock quantity:', product.stockQuantity);
            
            try {
                await fetch(`${API_BASE_URL}/products/${product.id}`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        name: name,
                        sku: sku,
                        price: parseFloat(price),
                        sportCategory: sportCategory,
                        productType: productType,
                        stockQuantity: parseInt(stockQuantity),
                        isAvailable: product.isAvailable
                    })
                });
                alert('Product updated!');
                this.loadBrandsAndProducts();
            } catch (error) {
                console.error('Error:', error);
                alert('Error updating product!');
            }
        },
        
        async deleteProduct(id) {
            if (!confirm('Delete this product?')) return;
            
            try {
                await fetch(`${API_BASE_URL}/products/${id}`, {
                    method: 'DELETE'
                });
                alert('Product deleted!');
                this.loadBrandsAndProducts();
            } catch (error) {
                console.error('Error:', error);
                alert('Error deleting product!');
            }
        }
    }
}).mount('#app');