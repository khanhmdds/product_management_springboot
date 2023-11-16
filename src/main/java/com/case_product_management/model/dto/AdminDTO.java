package com.case_product_management.model.dto.staff;

import com.case_product_management.model.LocationRegion;
import com.case_product_management.model.Admin;
import com.case_product_management.model.AdminAvatar;
import com.case_product_management.model.User;

import com.case_product_management.model.dto.LocationRegionDTO;
import com.codegym.model.dto.staffAvatar.StaffAvatarDTO;

import com.codegym.model.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StaffDTO {
    private Long id;

    @NotEmpty(message = "Vui lòng nhập tên nhân viên.")
    @Size(min = 5, max = 100, message = "Họ tên có độ dài nằm trong khoảng 5 - 100 ký tự")
    private String fullName;

    @NotEmpty(message = "Vui lòng nhập số điện thoại.")
    @Pattern(regexp = "^[+]*[(]?[0-9]{1,4}[)]?[-\\s./0-9]*$", message = "Số điện thoại không đúng định dạng.")
    private String phone;

    @Valid
    private LocationRegionDTO locationRegion;

    @Valid
    private UserDTO user;

    @Valid
    private StaffAvatarDTO staffAvatar;

    public StaffDTO(Long id, String fullName, String phone, LocationRegion locationRegion, User user, StaffAvatar staffAvatar) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.locationRegion = locationRegion.toLocationRegionDTO();
        this.user = user.toUserDTO();
        this.staffAvatar = staffAvatar.toStaffAvatarDTO();
    }

//    public Staff toStaff() {
//        return new Staff()
//                .setId(id)
//                .setFullName(fullName)
//                .setPhone(phone)
//                .setLocationRegion(locationRegion.toLocationRegion())
//                .setUser(user.toUser())
//                .setAvatar(staffAvatar.toStaffAvatar());
//    }
}
