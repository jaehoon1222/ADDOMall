package com.shop.controller;

import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.service.ItemService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Optional;
@Controller
@RequiredArgsConstructor
public class MainController {
    private final ItemService itemService;
    @GetMapping(value = "/")
    public String main(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model, Principal principal, HttpSession httpSession) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        if(itemSearchDto.getSearchQuery() == null)
        {
            itemSearchDto.setSearchQuery("");
        }
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);
        Page<MainItemDto> items_food = itemService.getFoodItemPage(itemSearchDto, pageable);
        Page<MainItemDto> items_snack = itemService.getSnackItemPage(itemSearchDto, pageable);
        Page<MainItemDto> items_beauty = itemService.getBeautyItemPage(itemSearchDto, pageable);
        Page<MainItemDto> items_fashion = itemService.getFashionItemPage(itemSearchDto, pageable);
        model.addAttribute("items", items);
        model.addAttribute("items_food", items_food);
        model.addAttribute("items_snack", items_snack);
        model.addAttribute("items_beauty", items_beauty);
        model.addAttribute("items_fashion", items_fashion);
        model.addAttribute("itemSearchDto",itemSearchDto);
        model.addAttribute("maxPage",5);
        return "main";
    }
}
