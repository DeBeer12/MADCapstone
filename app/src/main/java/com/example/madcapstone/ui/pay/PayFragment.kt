package com.example.madcapstone.ui.pay

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.madcapstone.R
import com.example.madcapstone.databinding.FragmentPayBinding
import com.example.madcapstone.db.Product
import com.example.madcapstone.db.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PayFragment : Fragment() {
    private lateinit var binding: FragmentPayBinding
    private lateinit var dashboardViewModel: PayViewModel
    private lateinit var productRepository: ProductRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)
    lateinit var list: List<Product>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
                ViewModelProvider(this).get(PayViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_pay, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn = view.findViewById<Button>(R.id.buttonPay)
        binding = FragmentPayBinding.inflate(layoutInflater)

        productRepository = ProductRepository(requireContext())

        initVars()

        val btnPay = btn
        Log.e("test", btnPay.toString())
        btnPay.setOnClickListener {
            Log.e("test", "In Button")
            showAddProductdialog()
        }
    }
    private fun initVars(){
        mainScope.launch {
            list = withContext(Dispatchers.IO) {
                productRepository.getAllProducts()
            }

            val tvFoodT: TextView = binding.tvFoodT
            val tvDrinksT: TextView = binding.tvDrinksT
            val tvMerchT: TextView = binding.tvMerchT
            if(list.isEmpty()){
                tvFoodT.text = 00.00.toString()
                tvFoodT.text = 00.00.toString()
                tvFoodT.text = 00.00.toString()
            } else {
                tvFoodT.text = list[1].toString()
                tvDrinksT.text = list[1].toString()
                tvMerchT.text = list[2].toString()
            }
        }
    }

    @SuppressLint("InflateParams")
    private fun showAddProductdialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.dialogTitle))
        val dialogLayout = layoutInflater.inflate(R.layout.add_product_dialog, null)
        val radioGroup: RadioGroup = dialogLayout.findViewById(R.id.rgStore)
        val productName = resources.getResourceEntryName(radioGroup.checkedRadioButtonId)
        val amount = dialogLayout.findViewById<EditText>(R.id.txt_amount)

        builder.setView(dialogLayout)
        builder.setPositiveButton(R.string.add) { _: DialogInterface, _: Int ->
            addProduct(productName, amount.toString())
        }
        builder.show()
    }

    private fun addProduct(productName:String, amount:String){
    if (validateFields(productName, amount)) {
        mainScope.launch {
            val product = Product(
                productText = amount.toDouble(),
                storeText = productName
            )
            val tvFood: TextView = binding.tvFood
            val tvDrinks: TextView = binding.tvDrinks
            val tvMerch: TextView = binding.tvMerch
            if(list.isNotEmpty()){
            when (productName) {
                tvFood.text -> {
                    list[0].productText += amount.toDouble()
                }
                tvDrinks.text -> {
                    list[1].productText += amount.toDouble()
                }
                tvMerch.text -> {
                    list[2].productText += amount.toDouble()
                }
            }
            }

            withContext(Dispatchers.IO) {
                productRepository.insertProduct(product)
            }
        }
    }
}

private fun validateFields(
    productName: String, amount: String
): Boolean {
    return if (productName.isNotBlank()
        && amount.isNotBlank()
    ) {
        true
    } else {
        Toast.makeText(activity, "Please fill in the fields", Toast.LENGTH_LONG).show()
        false
    }
}
}