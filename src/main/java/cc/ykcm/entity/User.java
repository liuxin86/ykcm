package cc.ykcm.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 用户表
 * @author liuxin
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("tb_user")
public class User {

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private long id;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @NotBlank(message="用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9@]*$", message = "用户名请以字母开头，只允许字母和数字")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 16, message = "密码长度应为6~16位")
    private String password;
    /**
     * 盐
     */
    private String salt;
    /**
     * 状态 0：禁用 1：正常
     */
    private Integer status;
    /**
     * 手机号
     */
    @Digits(message = "手机号必须是数字", fraction = 0, integer = 11)
    @Length(max = 11, min = 11, message = "手机号的长度必须是11位")
    private String phone;
    /**
     * 邮箱
     */
    @TableField(value = "email")
    @Email(message="邮箱格式不正确")
    private String email;
    /**
     * 个人签名
     */
    @TableField("filename")
    private String filename;
    /**
     * 最后登录时间
     */
    @TableField("last_login_time")
    private Date lastLoginTime;
}
