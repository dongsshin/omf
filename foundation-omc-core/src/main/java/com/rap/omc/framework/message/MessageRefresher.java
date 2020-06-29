package com.rap.omc.framework.message;


import org.springframework.context.MessageSource;
import org.springframework.context.support.DelegatingMessageSource;
import com.rap.omc.framework.message.exception.MessageException;

public class MessageRefresher
{
    public static final void refresh(MessageSource messageSource)
    {
        if (messageSource instanceof RefreshableMessageSource) {
            ((RefreshableMessageSource)messageSource).refresh();
            } else if (messageSource instanceof DelegatingMessageSource) {
            MessageSource parentMessageSource = ((DelegatingMessageSource)messageSource)
                    .getParentMessageSource();
            if (parentMessageSource != null) refresh(parentMessageSource);
            }
        else {
            throw new MessageException("Refresh is possible only RefreshableMessageSource type.");
        }
    }
    public static final void refreshIncludingAncestors(MessageSource messageSource)
    {
        if (messageSource instanceof RefreshableMessageSource) {
            ((RefreshableMessageSource)messageSource).refreshIncludingAncestors();
            } else if (messageSource instanceof DelegatingMessageSource) {
            MessageSource parentMessageSource = ((DelegatingMessageSource)messageSource)
                    .getParentMessageSource();
            if (parentMessageSource != null)/* 61 */ refreshIncludingAncestors(parentMessageSource);
            }
        else {
            throw new MessageException("Refresh is possible only RefreshableMessageSource type.");
        }
    }
}