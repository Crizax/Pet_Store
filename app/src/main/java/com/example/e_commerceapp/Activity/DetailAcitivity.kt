package com.example.e_commerceapp.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.e_commerceapp.Adapter.CartAdapter
import com.example.e_commerceapp.Adapter.PicListAdapter
import com.example.e_commerceapp.Adapter.SizeListAdapter
import com.example.e_commerceapp.Model.ItemsModel
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityDetailAcitivityBinding
import com.example.project1762.Helper.ManagmentCart

class DetailAcitivity : BaseActivity() {

    private lateinit var binding: ActivityDetailAcitivityBinding
    private lateinit var item: ItemsModel
    private var numberOrder = 1
    private lateinit var managementCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)



        managementCart = ManagmentCart(this)

        getBundle()
        initList()

    }

    private fun initList() {
        val sizeList = ArrayList<String>()
        for (size in item.size) {
            sizeList.add(size.toString())
        }

        binding.sizeList.adapter = SizeListAdapter(sizeList)
        binding.sizeList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val colorList = ArrayList<String>()
        for (imageUrl in item.picUrl) {
            colorList.add(imageUrl)
        }
        if (colorList.isNotEmpty()) {
            Glide.with(this)
                .load(colorList[0])
                .into(binding.picMain)
        }

        binding.picList.adapter = PicListAdapter(colorList, binding.picMain)
        binding.picList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.titleTxT.text = item.title
        binding.priceTxT.text = "$${item.price}"
        binding.ratingTxt.text = "${item.rating}"
        binding.descriptionTxt.text = item.description
        binding.SellerNameTxt.text = item.sellerName ?: "Unknown Seller"

        binding.AddToCartBtn.setOnClickListener {
            item.numberInCart = numberOrder
            managementCart.insertItems(item)
        }

        binding.backBtn.setOnClickListener { finish() }
        binding.CartButton.setOnClickListener {
            startActivity(Intent(this@DetailAcitivity , CartActivity::class.java))
        }

        if (!item.sellerPic.isNullOrEmpty()) {
            Glide.with(this)
                .load(item.sellerPic)
                .apply(RequestOptions().transform(CenterCrop()))
                .into(binding.picSeller)
        }

        binding.msgToSellerBtn.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_VIEW)
            sendIntent.data = Uri.parse("sms:${item.sellerTell}")
            sendIntent.putExtra("sms_body", "type your message")
            startActivity(sendIntent)
        }

        binding.callToSellerBtn.setOnClickListener {
            val phone = item.sellerTell.toString()
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        }


    }
}