def clasification():
    C = "Cat"
    F = "Fish"
    H = "Hen"

    realLabels =     [C, C, C, C, C, C, F, F, F, F, F, F, F, F, F, F, H, H, H, H, H, H, H, H, H]
    computedLabels = [C, C, C, C, H, F, C, C, C, C, C, C, H, H, F, F, C, C, C, H, H, H, H, H, H]

    accuracy_c, prec_c, recall_c = eval_classification(realLabels, computedLabels, C)
    accuracy_f, prec_f, recall_f = eval_classification(realLabels, computedLabels, F)
    accuracy_h, prec_h, recall_h = eval_classification(realLabels, computedLabels, H)

    print('Clasification')
    print('Cat:   accuracy = ', accuracy_c, ' precision = ', prec_c, ' recall = ', recall_c)
    print('Fish:  accuracy = ', accuracy_f, ' precision = ', prec_f, ' recall = ', recall_f)
    print('Hen:   accuracy = ', accuracy_h, ' precision = ', prec_h, ' recall = ', recall_h)
    print('accuracy: ', (accuracy_c + accuracy_f + accuracy_h) / 3,
          ' precision: ', (prec_c + prec_f + prec_h) / 3,
          'recall: ', (recall_c + recall_f + recall_h) / 3)


def eval_classification(real_labels, computed_labels, label):
    accuracy = sum([1 if real_labels[i] == computed_labels[i] else 0 for i in range(0, len(real_labels))]) \
               / len(real_labels)

    correctly_predicted = sum([1 if (real_labels[i] == label and computed_labels[i] == label)
                               else 0 for i in range(len(real_labels))])
    all_predicted = sum([1 if (computed_labels[i] == label)
                         else 0 for i in range(len(real_labels))])
    real = sum([1 if (real_labels[i] == label) else 0 for i in range(len(real_labels))])

    precision = correctly_predicted / all_predicted
    recall = correctly_predicted / real

    return accuracy, precision, recall
