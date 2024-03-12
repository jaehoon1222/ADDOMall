package com.shop.controller;

import com.shop.constant.Type;
import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final ItemService itemService;
    @GetMapping(value = {"/menu/food", "/menu/food/{page}"})
    public String foodList(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {

            Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 20);
            if (itemSearchDto.getSearchQuery() == null) {
                itemSearchDto.setSearchQuery("");
            }
            Page<MainItemDto> items = itemService.getFoodItemPage(itemSearchDto, pageable);
                model.addAttribute("items", items);
                model.addAttribute("itemSearchDto", itemSearchDto);
                model.addAttribute("maxPage", 5);

        return "category/food";
    }

    @GetMapping(value = {"/menu/snack", "/menu/snack/{page}"})
    public String snackList(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 20);
        if (itemSearchDto.getSearchQuery() == null) {
            itemSearchDto.setSearchQuery("");
        }
        Page<MainItemDto> items = itemService.getSnackItemPage(itemSearchDto, pageable);
        System.out.println(items.getContent().size());
        System.out.println(items.getTotalElements());
        System.out.println(items.getNumberOfElements());
        System.out.println(items.getNumber());
        System.out.println(items.getSize());
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "category/snack";
    }

    @GetMapping(value = {"/menu/beauty", "/menu/beauty/{page}"})
    public String beautyList(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 20);
        if (itemSearchDto.getSearchQuery() == null) {
            itemSearchDto.setSearchQuery("");
        }
        Page<MainItemDto> items = itemService.getBeautyItemPage(itemSearchDto, pageable);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "category/beauty";
    }

    @GetMapping(value = {"/menu/pad", "/menu/pad/{page}"})
    public String padList(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 20);
        if (itemSearchDto.getSearchQuery() == null) {
            itemSearchDto.setSearchQuery("");
        }
        Page<MainItemDto> items = itemService.getPadItemPage(itemSearchDto, pageable);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "category/pad";
    }

    @GetMapping(value = {"/menu/toy", "/menu/toy/{page}"})
    public String toyList(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 20);
        if (itemSearchDto.getSearchQuery() == null) {
            itemSearchDto.setSearchQuery("");
        }
        Page<MainItemDto> items = itemService.getToyItemPage(itemSearchDto, pageable);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "category/toy";
    }

    @GetMapping(value = {"/menu/fashion", "/menu/fashion/{page}"})
    public String fashionList(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 20);
        if (itemSearchDto.getSearchQuery() == null) {
            itemSearchDto.setSearchQuery("");
        }
        Page<MainItemDto> items = itemService.getFashionItemPage(itemSearchDto, pageable);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "category/fashion";
    }
}
