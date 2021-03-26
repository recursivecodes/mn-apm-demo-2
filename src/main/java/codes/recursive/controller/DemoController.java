package codes.recursive.controller;

import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.tracing.annotation.ContinueSpan;
import io.micronaut.tracing.annotation.SpanTag;

import java.util.List;

@Controller("/demo2")
public class DemoController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }

    @Get(uri="/favoriteNumber/{id}")
    @ContinueSpan
    public HttpResponse favoriteNumber(@SpanTag("user.id") int id) {
        List<String> nums = List.of("9", "11", "2", "4", "99", "33", "7", "1223", "3", "0");
        if (id < 1 || id > nums.size()) {
            return HttpResponse.notFound();
        } else {
            String num = nums.get(id-1);
            return HttpResponse.ok(
                    CollectionUtils.mapOf("favoriteNumber", num)
            );
        }
    }
}