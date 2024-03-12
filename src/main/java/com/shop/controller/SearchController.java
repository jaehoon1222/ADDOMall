package com.shop.controller;


import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.entity.Item;
import com.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import retrofit2.http.Path;


import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SearchController {
    private final ItemService itemService;

    @GetMapping(value = {"/search","/search/{page}"})
    public String itemSearch(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page,
                             Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 50);
        if(itemSearchDto.getSearchQuery() == null)
        {
            itemSearchDto.setSearchQuery("");
        }
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage",5);
        return "search/searchList";
    }
}
