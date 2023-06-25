package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationItemControllerV2 {




    private final ItemValidator itemValidator;
    private final ItemRepository itemRepository;

    @InitBinder
    public void init(WebDataBinder dataBinder){
        dataBinder.addValidators(itemValidator);
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }

   // @PostMapping("/add")
    public String addItemV1(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        // 검증 로직
        if(!StringUtils.hasText(item.getItemName())){

            bindingResult.addError(new FieldError("item","itemName",item.getItemName(),false,null,null,"상품 이름은 필수입니다."));
        }

        if(item.getPrice() ==null || item.getPrice() < 1000|| item.getPrice() > 1000000){
            bindingResult.addError(new FieldError("item","itemName",item.getPrice(),false,null,null,"가격은 1000원이상 100만원 미만"));
        }

        if(item.getQuantity() ==null|| item.getQuantity() >=9999){
            bindingResult.addError(new FieldError("item","quantity",item.getQuantity(),false,null
                    ,null,"수량은 최대 9999개까지만 가능하다."));
        }

        //특정 필드가 아닌 복합 룰 검증
        if(item.getPrice()!= null && item.getQuantity()!=null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){

                //필드가없고 글로벌 오류라서 ObejectError 사용한다.
                bindingResult.addError(new ObjectError("item",null,null,"가격 * 수량의 합은 10000원 이상이어야합니다. 현재 값: "+resultPrice));
            }
        }

        //검증에 실패하면 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}",bindingResult);
            //bindingResult가 자동으로 뷰에 담겨서 가므로 모델이 필요가 없어진다.
            return"validation/v2/addForm";
        }

        //성공 로직

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

   // @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        // 검증 로직
        if(!StringUtils.hasText(item.getItemName())){

            bindingResult.addError(new FieldError("item","itemName",item.getItemName(),false,null,null,"상품 이름은 필수입니다."));
        }

        if(item.getPrice() ==null || item.getPrice() < 1000|| item.getPrice() > 1000000){
            bindingResult.addError(new FieldError("item","itemName",item.getPrice(),false,null,null,"가격은 1000원이상 100만원 미만"));
        }

        if(item.getQuantity() ==null|| item.getQuantity() >=9999){
            bindingResult.addError(new FieldError("item","quantity",item.getQuantity(),false,null
                    ,null,"수량은 최대 9999개까지만 가능하다."));
        }

        //특정 필드가 아닌 복합 룰 검증
        if(item.getPrice()!= null && item.getQuantity()!=null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){

                //필드가없고 글로벌 오류라서 ObejectError 사용한다.
                bindingResult.addError(new ObjectError("item",null,null,"가격 * 수량의 합은 10000원 이상이어야합니다. 현재 값: "+resultPrice));
            }
        }

        //검증에 실패하면 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}",bindingResult);
            //bindingResult가 자동으로 뷰에 담겨서 가므로 모델이 필요가 없어진다.
            return"validation/v2/addForm";
        }

        //성공 로직

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

   // @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {



        // 검증 로직
        if(!StringUtils.hasText(item.getItemName())){

            bindingResult.addError(new FieldError("item","itemName",item.getItemName(),false,new String[]{"required.item.itemName"},null,null));
        }

        if(item.getPrice() ==null || item.getPrice() < 1000|| item.getPrice() > 1000000){
            bindingResult.addError(new FieldError("item","itemName",item.getPrice(),false,new String[]{"range.item.price"}, new Object[]{1000,10000000},null));
        }

        if(item.getQuantity() ==null|| item.getQuantity() >=9999){
            bindingResult.addError(new FieldError("item","quantity",item.getQuantity(),false,new String[]{"max.item.quantity"},new Object[]{9999},null));
        }


        //특정 필드가 아닌 복합 룰 검증
        if(item.getPrice()!= null && item.getQuantity()!=null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){

                //필드가없고 글로벌 오류라서 ObejectError 사용한다.
                bindingResult.addError(new ObjectError("item",new String[]{"totalPriceMin"},new Object[]{10000,resultPrice},null));
            }
        }

        //검증에 실패하면 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}",bindingResult);
            //bindingResult가 자동으로 뷰에 담겨서 가므로 모델이 필요가 없어진다.
            return"validation/v2/addForm";
        }

        //성공 로직

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

//    @PostMapping("/add")
    public String addItemV4(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        log.info("obejctName={}", bindingResult.getObjectName());
        log.info("target={}",bindingResult.getTarget());


        // 검증 로직
        if(!StringUtils.hasText(item.getItemName())){

            bindingResult.rejectValue("itemName","required",null);

        }

        if(item.getPrice() ==null || item.getPrice() < 1000|| item.getPrice() > 1000000){
            bindingResult.rejectValue("price","range", new Object[]{1000,10000000},null);
        }

        if(item.getQuantity() ==null|| item.getQuantity() >=9999){
            bindingResult.rejectValue("quantity","max");
        }


        //특정 필드가 아닌 복합 룰 검증
        if(item.getPrice()!= null && item.getQuantity()!=null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){

                //필드가없고 글로벌 오류라서 ObejectError 사용한다.
                bindingResult.reject("totalPriceMin");
            }
        }

        //검증에 실패하면 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}",bindingResult);
            //bindingResult가 자동으로 뷰에 담겨서 가므로 모델이 필요가 없어진다.
            return"validation/v2/addForm";
        }

        //성공 로직

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }



//    @PostMapping("/add")
    public String addItemV5(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {


        itemValidator.validate(item, bindingResult);

        log.info("obejctName={}", bindingResult.getObjectName());
        log.info("target={}",bindingResult.getTarget());


        // 검증 로직
        if(!StringUtils.hasText(item.getItemName())){

            bindingResult.rejectValue("itemName","required",null);

        }

        if(item.getPrice() ==null || item.getPrice() < 1000|| item.getPrice() > 1000000){
            bindingResult.rejectValue("price","range", new Object[]{1000,10000000},null);
        }

        if(item.getQuantity() ==null|| item.getQuantity() >=9999){
            bindingResult.rejectValue("quantity","max");
        }


        //특정 필드가 아닌 복합 룰 검증
        if(item.getPrice()!= null && item.getQuantity()!=null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){

                //필드가없고 글로벌 오류라서 ObejectError 사용한다.
                bindingResult.reject("totalPriceMin");
            }
        }

        //검증에 실패하면 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}",bindingResult);
            //bindingResult가 자동으로 뷰에 담겨서 가므로 모델이 필요가 없어진다.
            return"validation/v2/addForm";
        }

        //성공 로직

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }


    @PostMapping("/add")
    public String addItemV6(@Validated @ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        //특정 필드가 아닌 복합 룰 검증
        if(item.getPrice()!= null && item.getQuantity()!=null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){

                //필드가없고 글로벌 오류라서 ObejectError 사용한다.
                bindingResult.reject("totalPriceMin");
            }
        }

        //검증에 실패하면 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}",bindingResult);
            //bindingResult가 자동으로 뷰에 담겨서 가므로 모델이 필요가 없어진다.
            return"validation/v2/addForm";
        }

        //성공 로직

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
    }

}

